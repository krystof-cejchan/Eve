/**
 *
 */
package main.onStart;

import commands.CommandManager;
import commands.ICommands;

/**
 * @author krystof-cejchan
 *
 */
public class AverageTriggerCountFromCommands implements IOnStart {
    private int AverageTriggerCountFromCommands;

    @Override
    public void doYourPart() {
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
