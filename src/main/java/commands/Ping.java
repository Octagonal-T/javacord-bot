package commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.entity.message.Message;

import java.util.HashMap;

public class Ping extends BaseCommand{
  private final HashMap<String, BaseCommand> commands;
  public Ping(HashMap<String, BaseCommand> commands){
    this.commands=commands;
  }
  @Override
  public boolean execute(MessageCreateEvent event, String[] args){
    Message message = event.getChannel().sendMessage("Pong!").join();
    long ms = message.getCreationTimestamp().toEpochMilli()-event.getMessage().getCreationTimestamp().toEpochMilli();
    message.edit("Ping! "+ms+"MS");
    return true;
  }

  @Override
  public String[] getInfo() {
    final String description = "Gets the ping of the bot";
    final String usage = "ping";
    final String name = "ping";
    return new String[]{name, description, usage};
  }
}
