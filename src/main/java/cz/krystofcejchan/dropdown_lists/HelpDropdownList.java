package cz.krystofcejchan.dropdown_lists;

import cz.krystofcejchan.commands.commands_slash.Help;
import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.SlashCommandManager;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

public class HelpDropdownList implements IDropdownList {
    @Override
    public void handleEvent(SelectMenuInteractionEvent event) {
        event.replyEmbeds(generateMsgTextForCommandCategory(SlashCommandCategory.valueOf(event.getInteraction()
                .getSelectedOptions().get(0).getValue())).build()).setEphemeral(true).queue();

    }

    private EmbedBuilder generateMsgTextForCommandCategory(SlashCommandCategory category) {
        EmbedBuilder builder = new EmbedBuilder();
        new SlashCommandManager().getAllCommands().forEach(cmd -> {
            if (cmd.getCategory().stream().anyMatch(commandCategory -> commandCategory.equals(category))) {
                builder.addField(cmd.getName(), cmd.getDescription() +
                        "; Takes Arguments: " + (cmd.takesArguments()), false);
            }
        });

        return builder.setColor(UtilityClass.getRandomColor())
                .setTitle("All '" + SlashCommandCategory.getCategoryInFullName(category) + "' commands");
    }

    @Override
    public String getIdentificator() {
        return "help_page_scroller";
    }

    @Override
    public ISlashCommands representativeCommand() {
        return new Help();
    }


}
