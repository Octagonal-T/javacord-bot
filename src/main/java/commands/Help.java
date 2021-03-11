package commands;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Help extends BaseCommand{
  private final HashMap<String, BaseCommand> commands;
  public Help(HashMap<String, BaseCommand> commands){
    this.commands=commands;
  }
  @Override
  public boolean execute(MessageCreateEvent event, String[] args) {
    if(args[0].equals("")) {
      StringBuilder help = new StringBuilder();
      for (Map.Entry<String, BaseCommand> entry : commands.entrySet()) {
        String key = entry.getKey();
        BaseCommand value = entry.getValue();

        help.append("\n`").append(key).append("` ").append(value.getInfo()[1]);
      }
      event.getChannel().sendMessage(new EmbedBuilder()
              .setAuthor(event.getMessageAuthor())
              .setTitle("Help!")
              .setColor(Color.red)
              .setDescription(help.toString())
              .setTimestampToNow()
      );
      return true;
    }else{
      if(commands.containsKey(args[0].toLowerCase(Locale.ROOT))){
        event.getChannel().sendMessage(new EmbedBuilder()
                .setTimestampToNow()
                .setAuthor(event.getMessageAuthor())
                .setTitle("Help!")
                .setColor(Color.blue)
                .setDescription("`"+args[0].toLowerCase(Locale.ROOT)+"` "+commands.get(args[0].toLowerCase(Locale.ROOT)).getInfo()[1])
                .addField("Usage","`"+commands.get(args[0].toLowerCase(Locale.ROOT)).getInfo()[2]+"`"));
        return true;
      }else{
        event.getChannel().sendMessage("Not a valid command!");
        return false;
      }
    }
  }

  @Override
  public String[] getInfo() {
    final String description = "Gets all of the commands.";
    final String usage = "help [command]";
    final String name = "help";
    return new String[]{name,description,usage};
  }
}
