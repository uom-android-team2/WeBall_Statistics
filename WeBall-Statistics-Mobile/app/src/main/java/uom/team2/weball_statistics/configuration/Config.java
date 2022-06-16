package uom.team2.weball_statistics.configuration;

public class Config {
    
    public static final String IP = "172.23.96.1";
    public static final String API_URL = "http://" + IP + "/WeBall_Statistics-Backend/API/";
    //We will use the format bellow for the apis.
    public static final String MATCHES_API = "http://" + IP + "/weBall_Statistics-Backend/API/match.php";
    public static final String API_REFEREE = "referee.php";
    public static final String API_PLAYER_STATS_LIVE = "playerLiveStatistics.php";
    public static final String API_TEAM_STATS_LIVE = "teamLiveStatistics.php";
    public static final String API_ΤΕΑΜ_STATISTICS_COMPLETED = "teamStatisticsCompleted.php";
    public static final String PLAYER_ID = "player_id";
    public static final String API_PLAYER_STATISTICS_COMPLETED = "playerStatisticsCompleted.php";
    public static final String TEAM_ID = "team_id";
    public static final String PLAYER_IMAGES_RESOURCES = "http://" + IP + "/WeBall_Statistics-Backend/resources/player_images/";
    public static final String TEAM_IMAGES_RESOURCES = "http://" + IP + "/WeBall_Statistics-Backend/resources/team_images/";

    public static final int COEFFICIENT_WIN = 2;
    public static final int COEFFICIENT_LOOSE = 1;
    public static final int COEFFICIENT_THREE_POINT = 3;
    public static final int COEFFICIENT_TWO_POINT = 2;
    public static final int COEFFICIENT_ONE_POINT = 1;
    public static String PLAYER = "player.php";
    public static String TEAM = "team.php";
    public static String MATCH = "match.php";

}
