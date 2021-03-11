package commands;

import org.javacord.api.event.message.MessageCreateEvent;
public abstract class BaseCommand {
  public abstract boolean execute(MessageCreateEvent event, String[] args);
  public abstract String[] getInfo();
}
