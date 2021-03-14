import commands.BaseCommand;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

public class MessageHandler implements MessageCreateListener{
  final HashMap<String, BaseCommand> commands;
  public MessageHandler(HashMap<String, BaseCommand> commands){
    this.commands=commands;
  }
  @Override
  public void onMessageCreate(MessageCreateEvent event){
    String prefix = Constants.prefix;
    if(!event.getMessageAuthor().isBotUser()) {
      String messageContent = event.getMessageContent();
      if (messageContent.toLowerCase(Locale.ROOT).startsWith(prefix)) {
        String command = messageContent.substring(prefix.length()).trim().split(" ")[0].toLowerCase(Locale.ROOT);
        String[] args = messageContent.substring(prefix.length() + command.length()).trim().split(" ");
        System.out.println(Arrays.toString(args));
        System.out.println(command);
        if (commands.containsKey(command)) {
          commands.get(command).execute(event, args);
        } else {
          event.getChannel().sendMessage("Not a valid command! Do `" + prefix + "help` to see all of the commands!");
        }
      }
    }
  }

}
