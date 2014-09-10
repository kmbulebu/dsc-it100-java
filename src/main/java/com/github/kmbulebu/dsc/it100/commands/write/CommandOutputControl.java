package com.github.kmbulebu.dsc.it100.commands.write;

import com.github.kmbulebu.dsc.it100.commands.ICommandHelp;

public class CommandOutputControl extends WriteCommand implements ICommandHelp {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3447190568071046840L;
	
	public static final String CODE = "020";
	
	private final int partition;
	private final int pgm;

	public CommandOutputControl(int partition, int pgm) {
		super(CODE, Integer.toString(partition) + Integer.toString(pgm));
		this.partition = partition;
		this.pgm = pgm;
	}


	public String getDescription() {
		return "Activates the selected Command Output. (1-4) on the selected partition (1-8)";
	}

	public int getPartition() {
		return partition;
	}


	public int getPgm() {
		return pgm;
	}

}
