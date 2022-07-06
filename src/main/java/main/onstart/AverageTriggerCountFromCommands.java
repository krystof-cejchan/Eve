/**
 *
 */
package main.onstart;

import commands.CommandManager;
import commands.ICommands;

import java.util.List;

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
        List<ICommands> list = new CommandManager().getAllCommands();
        for (ICommands iCommands : list) {
            helper += iCommands.getTriggers().size();
        }
        return helper / list.size();
    }

}
