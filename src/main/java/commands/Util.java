package commands;

import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.entity.user.User;

import java.util.List;
import java.util.ArrayList;

public class Util {
  public static List<User> getMention(MessageCreateEvent event, String[] args){
    List<User> users = event.getMessage().getMentionedUsers();
    users = new ArrayList<>(users);
      if (event.getServer().isPresent()) {
        Server server = event.getServer().get();
        try {
          if (server.getMemberById(args[0]).isPresent()) users.add(server.getMemberById(args[0]).get());
          else if (server.getMemberByDiscriminatedNameIgnoreCase(args[0]).isPresent())
            users.add(server.getMemberByDiscriminatedNameIgnoreCase(args[0]).get());
        }catch(UnsupportedOperationException e){
          e.printStackTrace();
        }

      }
    return users;
  }
}
