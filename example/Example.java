import me.gong.mcleaks.MCLeaksAPI;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Example {
    public static void main(String[] args) {

        MCLeaksAPI api = MCLeaksAPI.builder()
                .threadCount(2)
                .expireAfter(10, TimeUnit.MINUTES).build();

        {
            MCLeaksAPI noCache = MCLeaksAPI.builder()
                    .threadCount(2)
                    .nocache().build();
        }

        final String playerName = "BwA_BOOMSTICK";
        final UUID playerUUID = UUID.fromString("071a1906-a49c-4eaa-9156-98df14c2c72b"); // ideally gotten from a real player

        // -- async calling

        // no longer recommended
        api.checkAccount(playerName, isMCLeaks ->
                System.out.println("Got: " + isMCLeaks), Throwable::printStackTrace);

        // recommended
        api.checkAccount(playerUUID, isMCLeaks ->
                System.out.println("Got: " + isMCLeaks), Throwable::printStackTrace);

        // -- sync calling

        // not recommended
        final MCLeaksAPI.Result nameResult = api.checkAccount(playerName);
        // use your result

        // recommended
        final MCLeaksAPI.Result uuidResult = api.checkAccount(playerUUID);
        // use your result

        // clean up (usually in onDisable)
        api.shutdown();
    }
}