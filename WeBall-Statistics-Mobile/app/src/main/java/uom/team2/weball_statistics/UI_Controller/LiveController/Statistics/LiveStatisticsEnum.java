package uom.team2.weball_statistics.UI_Controller.LiveController.Statistics;

import uom.team2.weball_statistics.Model.PlayerLiveStatistics;
import uom.team2.weball_statistics.Model.TeamLiveStatistics;

/*
 * @author Leonard Pepa ics20033
 */
public enum LiveStatisticsEnum {
    successful_effort,
    total_effort,
    successful_freethrow,
    total_freethrow,
    succesful_twopointer,
    total_twopointer,
    succesful_threepointer,
    total_threepointer,
    steal,
    assist,
    block,
    rebound,
    foul,
    turnover;

    public static int getStatisticValueByName(TeamLiveStatistics teamLiveStatistics, LiveStatisticsEnum statisticEnum){
        switch (statisticEnum){
            case successful_effort:
                return teamLiveStatistics.getSuccessful_effort();
            case total_effort:
                return teamLiveStatistics.getTotal_effort();
            case successful_freethrow:
                return teamLiveStatistics.getSuccessful_freethrow();
            case total_freethrow:
                return teamLiveStatistics.getTotal_freethrow();
            case succesful_twopointer:
                return teamLiveStatistics.getSuccesful_twopointer();
            case total_twopointer:
                return teamLiveStatistics.getTotal_twopointer();
            case succesful_threepointer:
                return teamLiveStatistics.getSuccesful_threepointer();
            case total_threepointer:
                return teamLiveStatistics.getTotal_threepointer();
            case steal:
                return teamLiveStatistics.getSteal();
            case assist:
                return teamLiveStatistics.getAssist();
            case block:
                return teamLiveStatistics.getBlock();
            case rebound:
                return teamLiveStatistics.getRebound();
            case foul:
                return teamLiveStatistics.getFoul();
            case turnover:
                return teamLiveStatistics.getTurnover();
            default:
                return -1;
        }
    }

    public static int getStatisticValueByName(PlayerLiveStatistics playerLiveStatistics, LiveStatisticsEnum statisticEnum){
        switch (statisticEnum){
            case successful_effort:
                return playerLiveStatistics.getSuccessful_effort();
            case total_effort:
                return playerLiveStatistics.getTotal_effort();
            case successful_freethrow:
                return playerLiveStatistics.getSuccessful_freethrow();
            case total_freethrow:
                return playerLiveStatistics.getTotal_freethrow();
            case succesful_twopointer:
                return playerLiveStatistics.getSuccesful_twopointer();
            case total_twopointer:
                return playerLiveStatistics.getTotal_twopointer();
            case succesful_threepointer:
                return playerLiveStatistics.getSuccesful_threepointer();
            case total_threepointer:
                return playerLiveStatistics.getTotal_threepointer();
            case steal:
                return playerLiveStatistics.getSteal();
            case assist:
                return playerLiveStatistics.getAssist();
            case block:
                return playerLiveStatistics.getBlock();
            case rebound:
                return playerLiveStatistics.getRebound();
            case foul:
                return playerLiveStatistics.getFoul();
            case turnover:
                return playerLiveStatistics.getTurnover();
            default:
                return -1;
        }
    }




}
