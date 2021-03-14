import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.user.UserStatus;

public class Bot {
  public Bot(){}

  public static void main(String[] args){
    new Constants();
    DiscordApi bot = new DiscordApiBuilder()
            .setToken(Constants.token)
            .setAllIntents()
            .login().join();
    bot.updateStatus(UserStatus.IDLE);
    bot.updateActivity(ActivityType.COMPETING, "Finishing by the end of March!");
    bot.addListener(new MessageHandler(Constants.commands));
  }
}
