package com.github.kmbulebu.dsc.it100;

import com.github.kmbulebu.dsc.it100.commands.read.BaudRateSetCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.util.Key;
import com.github.kmbulebu.dsc.it100.commands.write.BaudRateChangeCommand;
import com.github.kmbulebu.dsc.it100.commands.write.KeyPressCommand;
import com.github.kmbulebu.dsc.it100.commands.write.WriteCommand;
import rx.Observable;
import rx.functions.Action1;

import java.util.Scanner;

import static com.github.kmbulebu.dsc.it100.commands.write.BaudRateChangeCommand.BaudRate.BAUD_115200;

public class VirtualKeypadExample {

	public static void main(String... args) throws Exception {
		final Scanner scanner = new Scanner(System.in);
		System.out.print("Connect using socket or serial port?(s/p): ");
		String command = scanner.nextLine();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		String baud = null;
		if ("s".equalsIgnoreCase(command)) {
			System.out.print("Using socket. Insert host and port (host:port): ");
			command = scanner.nextLine();
			String[] socket = command.split(":");
			cb.withRemoteSocket(socket[0], Integer.parseInt(socket[1]));
		} else {
			System.out.print("Using serial port. Insert file name and baud rate (file baud): ");
			command = scanner.nextLine();
			String[] socket = command.split(" ");
			baud = socket[1];
			cb.withSerialPort(socket[0], Integer.parseInt(socket[1]));
		}

		final IT100 it100 = new IT100(cb.build());

		try {
			// Start communicating with IT-100.
			it100.connect();

			final Observable<ReadCommand> readObservable = it100.getReadObservable();

			//Write out responses
			readObservable.subscribe(new Action1<ReadCommand>() {

				@Override
				public void call(ReadCommand t1) {
					System.out.println("Received message: " + t1.toString());
				}

			});

			it100.getWriteObservable().subscribe(new Action1<WriteCommand>() {

				@Override
				public void call(WriteCommand t1) {
					System.out.println("Sent message: " + t1.toString());
				}

			});

			//Virtual keypad needs higher baud rate than 9600
			readObservable.ofType(BaudRateSetCommand.class).subscribe(new Action1<BaudRateSetCommand>() {

				@Override
				public void call(BaudRateSetCommand t1) {
					System.out.println("Baud rate successfully set to " + t1.getBaudRateCode());
					System.out.println("Run the program again with the new baud rate");
					System.exit(0);
				}

			});

			if ("9600".equals(baud)) {
				it100.send(new BaudRateChangeCommand(BAUD_115200));
			} else {
				readCommand(scanner, it100);
				System.out.println("Quitting");
				System.exit(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
			it100.disconnect();
		}
	}

	private static void readCommand(Scanner scanner, IT100 it100) {
		System.out.print("Insert command (or q to quit):");
		String command = scanner.nextLine();

		if (command.equals("q")) {
			return;
		}
		char unknownChar = 0;
		for (char c : command.toCharArray()) {
			Key key = Key.fromAsciiChar(c);
			if (key == null) {
				unknownChar = c;
				break;
			}
		}
		if (unknownChar != 0) {
			System.out.printf("Unknown character %c\n", unknownChar);
		} else {
			for (char c : command.toCharArray()) {
				Key key = Key.fromAsciiChar(c);
				it100.send(new KeyPressCommand(key));
				it100.send(new KeyPressCommand(Key.BREAK));
			}
		}
		readCommand(scanner, it100);
	}
}
