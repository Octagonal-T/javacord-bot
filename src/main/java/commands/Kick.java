package commands;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.entity.user.User;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class Kick extends BaseCommand{
  private final HashMap<String, BaseCommand> commands;
  public Kick(HashMap<String, BaseCommand> commands){
    this.commands=commands;
  }
  @Override
  public boolean execute(MessageCreateEvent event, String[] args) {
    if(!event.isServerMessage()){
      event.getChannel().sendMessage("You need to be in a server for this command to work!");
      return false;
    }

    if(args[0].equals("")){
      event.getChannel().sendMessage("You have to include who you want to kick!");
      return false;
    }
    List<User> mentionUsers = Util.getMention(event, args);
    if(mentionUsers.isEmpty()){
      event.getChannel().sendMessage("You have to include who you want to kick!!");
      return false;
    }
    User user = mentionUsers.get(0);
    event.getServer().ifPresent(server -> {
      if(server.canKickUsers(event.getMessageAuthor().asUser().get())) {
        if (server.canYouKickUser(user)) {
          StringBuilder reason = new StringBuilder();
          for (int i = 1; i < args.length; i++) {
            reason.append(" ").append(args[i]);
          }
          server.kickUser(user, reason.toString().trim());
          event.getChannel().sendMessage(new EmbedBuilder()
                  .setTimestampToNow()
                  .setTitle("Kicked!")
                  .setDescription("Kicked " + user.getDiscriminatedName() + " for the reason " + (!reason.toString().equals("") ? reason.toString() : "no reason"))
                  .setColor(Color.blue)
          );
        }else{
          event.getChannel().sendMessage(new EmbedBuilder()
                  .setTimestampToNow()
                  .setTitle("INVALID PERMISSIONS!")
                  .setDescription("Invalid permissions! I need to be able to kick members to use this command! If I do, make sure that my highest role is above the person you want to kick's highest role, that they aren't the owner of the server, and that they don't have kicking perms.")
                  .setColor(Color.RED)
          );
        }
      }else{
        event.getChannel().sendMessage(new EmbedBuilder()
                .setTimestampToNow()
                .setTitle("INVALID PERMISSIONS!")
                .setDescription("Invalid permissions! You need to be able to kick members to use this command! If you do, make sure that your highest role is above the person you want to kick's highest role, that they aren't the owner of the server, and that they don't have kicking perms.")
                .setColor(Color.RED)
        );
      }
    });
    return true;
  }

  @Override
  public String[] getInfo() {
    final String description = "Kicks a user from the server.";
    final String usage = "kick <user> [reason]";
    final String name = "kick";
    return new String[]{name, description, usage};
  }
}
