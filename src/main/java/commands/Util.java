package commands;

import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.entity.user.User;

import java.util.List;

public class Util {
  public static List<User> getMention(MessageCreateEvent event, String[] args){
    List<User> users = event.getMessage().getMentionedUsers();
    for (String arg : args) {
      if (event.getServer().isPresent()) {
        Server server = event.getServer().get();
        if (server.getMemberById(arg).isPresent()) users.add(server.getMemberById(arg).get());
        else if (server.getMemberByDiscriminatedNameIgnoreCase(arg).isPresent())
          users.add(server.getMemberByDiscriminatedNameIgnoreCase(arg).get());
      }
    }
    return users;
  }
}
