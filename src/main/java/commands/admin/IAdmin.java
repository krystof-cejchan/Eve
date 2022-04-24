package commands.admin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface IAdmin {
    void commitAdminOperation(MessageReceivedEvent event);
   String[] getTags();
   static String usersOK(){
       return "348358747825111040";
   }
   static boolean isVerified(MessageReceivedEvent event){
       return event.getAuthor().getId().equals(usersOK());
   }
}
