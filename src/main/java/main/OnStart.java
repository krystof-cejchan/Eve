package main;

import commands.CommandManager;
import commands.ICommands;

public class OnStart {
	private int AverageTriggerCountFromCommands;

	public OnStart() {
		setAverageTriggerCountFromCommands(calcAverageTriggerCount());
	}

	public int getAverageTriggerCountFromCommands() {
		return AverageTriggerCountFromCommands;
	}

	public void setAverageTriggerCountFromCommands(int averageTriggerCountFromCommands) {
		AverageTriggerCountFromCommands = averageTriggerCountFromCommands;
	}

	public int calcAverageTriggerCount() {
		int helper = 0;
		for (ICommands iCommands : CommandManager.getAllCommands()) {
			helper += iCommands.getTriggers().size();
		}
		return helper / CommandManager.getAllCommands().size();
	}

}
