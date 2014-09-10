package com.github.kmbulebu.dsc.it100;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.subjects.PublishSubject;

import com.github.kmbulebu.dsc.it100.commands.read.BroadcastLabelsCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.write.LabelsRequestCommand;
import com.github.kmbulebu.dsc.it100.commands.write.WriteCommand;

public class Labels implements Observer<BroadcastLabelsCommand> {
	
	private final Map<Integer, String> zoneLabels = new HashMap<Integer, String>();
	private final Map<Integer, String> partitionLabels = new HashMap<Integer, String>();
	private final Map<Integer, String> commandLabels = new HashMap<Integer, String>();
	
	public Labels(Observable<ReadCommand> readObservable, PublishSubject<WriteCommand> writeObservable) {
		readObservable.ofType(BroadcastLabelsCommand.class).subscribe(this);
		writeObservable.onNext(new LabelsRequestCommand());
	}
	
	public String getZoneLabel(int zone) {
		return zoneLabels.get(zone);
	}
	
	public String getPartitionLabel(int partition) {
		return partitionLabels.get(partition);
	}
	
	public String getCommandOutputLabel(int partition, int commandOutput) {
		return partitionLabels.get(mapCommandOutput(partition, commandOutput));
	}
	
	private int mapCommandOutput(int partition, int commandOutput) {
		return partition * 100 + commandOutput;
	}

	@Override
	public void onNext(BroadcastLabelsCommand command) {
		switch (command.getType()) {
		case ALARM_WHEN_ARMED:
			break;
		case COMMAND_OUTPUT:
			commandLabels.put(mapCommandOutput(command.getPartition(), command.getCommandOutput()), command.getLabel());
			break;
		case FAILED_TO_ARM:
			break;
		case FIRE_ALARM:
			break;
		case PARTITION:
			partitionLabels.put(command.getPartition(), command.getLabel());
			break;
		case ZONE:
			zoneLabels.put(command.getZone(), command.getLabel());
			break;
		default:
			break;
		
		}
	}

	@Override
	public void onCompleted() {
		// Not implemented.
	}

	@Override
	public void onError(Throwable e) {
		// Not implemented
	}

}
