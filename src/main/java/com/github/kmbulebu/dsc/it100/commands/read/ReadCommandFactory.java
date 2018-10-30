package com.github.kmbulebu.dsc.it100.commands.read;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand.CommandLengthException;
import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand.InvalidCommandException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;


public class ReadCommandFactory {

    private static Map<String, Class<? extends ReadCommand>> codeToCommandMap = new HashMap<String, Class<? extends ReadCommand>>();

    static {
        codeToCommandMap.put(AcknowledgeCommand.CODE, AcknowledgeCommand.class);
        codeToCommandMap.put(BaudRateSetCommand.CODE, BaudRateSetCommand.class);
        codeToCommandMap.put(BroadcastLabelsCommand.CODE, BroadcastLabelsCommand.class);
        codeToCommandMap.put(CommandErrorCommand.CODE, CommandErrorCommand.class);
        codeToCommandMap.put(CommandOutputInProgressCommand.CODE, CommandOutputInProgressCommand.class);
        codeToCommandMap.put(EntryDelayInProgressCommand.CODE, EntryDelayInProgressCommand.class);
        codeToCommandMap.put(ExitDelayInProgressCommand.CODE, ExitDelayInProgressCommand.class);
        codeToCommandMap.put(GeneralSystemTamperCommand.CODE, GeneralSystemTamperCommand.class);
        codeToCommandMap.put(GeneralSystemTamperRestoreCommand.CODE, GeneralSystemTamperRestoreCommand.class);
        codeToCommandMap.put(InvalidAccessCodeCommand.CODE, InvalidAccessCodeCommand.class);
        codeToCommandMap.put(KeybusFaultCommand.CODE, KeybusFaultCommand.class);
        codeToCommandMap.put(KeybusFaultRestoredCommand.CODE, KeybusFaultRestoredCommand.class);
        codeToCommandMap.put(LCDUpdateCommand.CODE, LCDUpdateCommand.class);
        codeToCommandMap.put(LCDCursorCommand.CODE, LCDCursorCommand.class);
        codeToCommandMap.put(LEDStatusCommand.CODE, LEDStatusCommand.class);
        codeToCommandMap.put(BeepStatusCommand.CODE, BeepStatusCommand.class);
        codeToCommandMap.put(BuzzerStatusCommand.CODE, BuzzerStatusCommand.class);
        codeToCommandMap.put(DoorChimeStatusCommand.CODE, DoorChimeStatusCommand.class);
        codeToCommandMap.put(ToneStatusCommand.CODE, ToneStatusCommand.class);
        codeToCommandMap.put(PanelACTroubleCommand.CODE, PanelACTroubleCommand.class);
        codeToCommandMap.put(PanelACTroubleRestoreCommand.CODE, PanelACTroubleRestoreCommand.class);
        codeToCommandMap.put(PanelBatteryTroubleCommand.CODE, PanelBatteryTroubleCommand.class);
        codeToCommandMap.put(PanelBatteryTroubleRestoreCommand.CODE, PanelBatteryTroubleRestoreCommand.class);
        codeToCommandMap.put(PartitionArmedCommand.CODE, PartitionArmedCommand.class);
        codeToCommandMap.put(PartitionBusyCommand.CODE, PartitionBusyCommand.class);
        codeToCommandMap.put(PartitionDisarmedCommand.CODE, PartitionDisarmedCommand.class);
        codeToCommandMap.put(PartitionInAlarmCommand.CODE, PartitionInAlarmCommand.class);
        codeToCommandMap.put(PartitionNotReadyCommand.CODE, PartitionNotReadyCommand.class);
        codeToCommandMap.put(PartitionReadyCommand.CODE, PartitionReadyCommand.class);
        codeToCommandMap.put(PartitionReadyToForceArmCommand.CODE, PartitionReadyToForceArmCommand.class);
        codeToCommandMap.put(RingDetectedCommand.CODE, RingDetectedCommand.class);
        codeToCommandMap.put(SystemBellTroubleCommand.CODE, SystemBellTroubleCommand.class);
        codeToCommandMap.put(SystemBellTroubleRestoreCommand.CODE, SystemBellTroubleRestoreCommand.class);
        codeToCommandMap.put(SystemErrorCommand.CODE, SystemErrorCommand.class);
        codeToCommandMap.put(TimeDateBroadcastCommand.CODE, TimeDateBroadcastCommand.class);
        codeToCommandMap.put(TroubleStatusCommand.CODE, TroubleStatusCommand.class);
        codeToCommandMap.put(TroubleStatusRestoreCommand.CODE, TroubleStatusRestoreCommand.class);
        codeToCommandMap.put(ZoneAlarmCommand.CODE, ZoneAlarmCommand.class);
        codeToCommandMap.put(ZoneAlarmRestoreCommand.CODE, ZoneAlarmRestoreCommand.class);
        codeToCommandMap.put(ZoneFaultCommand.CODE, ZoneFaultCommand.class);
        codeToCommandMap.put(ZoneFaultRestoreCommand.CODE, ZoneFaultRestoreCommand.class);
        codeToCommandMap.put(ZoneOpenCommand.CODE, ZoneOpenCommand.class);
        codeToCommandMap.put(ZoneRestoredCommand.CODE, ZoneRestoredCommand.class);
        codeToCommandMap.put(ZoneTamperCommand.CODE, ZoneTamperCommand.class);
        codeToCommandMap.put(ZoneTamperRestoreCommand.CODE, ZoneTamperRestoreCommand.class);
        codeToCommandMap.put(UserOpeningCommand.CODE, UserOpeningCommand.class);
        codeToCommandMap.put(UserClosingCommand.CODE, UserClosingCommand.class);
        codeToCommandMap.put(PartialClosingCommand.CODE, PartialClosingCommand.class);
        codeToCommandMap.put(SpecialClosingCommand.CODE, SpecialClosingCommand.class);
        codeToCommandMap.put(SoftwareVersionCommand.CODE, SoftwareVersionCommand.class);
    }


    public ReadCommand parseCommand(String receivedCommand) throws InvalidCommandException {
        if (receivedCommand.length() < 3) {
            throw new CommandLengthException(receivedCommand.length());
        }

        final String commandCode = receivedCommand.substring(0, 3);

        Class<? extends ReadCommand> commandClass = codeToCommandMap.get(commandCode);
        //System.out.println(commandCode + ", " + commandClass);
        if (commandClass == null) {
            throw new ReadCommand.UnknownCommandException(receivedCommand);
        }

        ReadCommand command;
        try {
            command = commandClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                NoSuchMethodException |
                InvocationTargetException e) {
            throw new InvalidCommandException(e);
        }
        command.init(receivedCommand);
        return command;
    }

    public static void registerCommand(String commandCode, Class<? extends ReadCommand> clazz) {
        if (codeToCommandMap.containsKey(commandCode)) {
            throw new IllegalArgumentException("You may not redefine an existing command.");
        }
        codeToCommandMap.put(commandCode, clazz);
    }
}
