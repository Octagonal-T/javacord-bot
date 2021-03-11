import commands.*;
import java.util.HashMap;

public class Constants {
  public final static String prefix = "oct!";
  public final static HashMap<String, BaseCommand> commands = new HashMap<>();
  public final static String token="NzQxMDIwMTM3NzAzODY2NDE4.XyxeyA.0_cfdoGjEiP5Az2W-CmClc8Ytjs";
  public Constants(){
    commands.put("ping", new Ping(commands));
    commands.put("help", new Help(commands));
    commands.put("kick", new Kick(commands));
  }

}
