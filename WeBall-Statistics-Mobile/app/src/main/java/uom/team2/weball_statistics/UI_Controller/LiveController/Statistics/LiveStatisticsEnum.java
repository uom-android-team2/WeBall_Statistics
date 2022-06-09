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


    public static void updateStatistic(PlayerLiveStatistics playerLiveStatistics, LiveStatisticsEnum liveStatisticsEnum){
        switch (liveStatisticsEnum){
            case successful_effort:
                playerLiveStatistics.setSuccessful_effort(playerLiveStatistics.getSuccessful_effort() + 1);
                break;
            case total_effort:
                playerLiveStatistics.setTotal_effort(playerLiveStatistics.getTotal_effort() + 1);
                break;
            case successful_freethrow:
                playerLiveStatistics.setSuccessful_freethrow(playerLiveStatistics.getSuccessful_freethrow() + 1);
                break;
            case total_freethrow:
                playerLiveStatistics.setTotal_freethrow(playerLiveStatistics.getTotal_freethrow() + 1);
                break;
            case succesful_twopointer:
                playerLiveStatistics.setSuccesful_twopointer(playerLiveStatistics.getSuccesful_twopointer() + 1);
                break;
            case total_twopointer:
                playerLiveStatistics.setTotal_twopointer(playerLiveStatistics.getTotal_twopointer() + 1);
                break;
            case succesful_threepointer:
                playerLiveStatistics.setSuccesful_threepointer(playerLiveStatistics.getSuccesful_threepointer() + 1);
                break;
            case total_threepointer:
                playerLiveStatistics.setTotal_threepointer(playerLiveStatistics.getTotal_threepointer() + 1);
                break;
            case steal:
                playerLiveStatistics.setSteal(playerLiveStatistics.getSteal() + 1);
                break;
            case assist:
                playerLiveStatistics.setAssist(playerLiveStatistics.getAssist() + 1);
                break;
            case block:
                playerLiveStatistics.setBlock(playerLiveStatistics.getBlock() + 1);
                break;
            case rebound:
                playerLiveStatistics.setRebound(playerLiveStatistics.getRebound() + 1);
                break;
            case foul:
                playerLiveStatistics.setFoul(playerLiveStatistics.getFoul() + 1);
                break;
            case turnover:
                playerLiveStatistics.setTurnover(playerLiveStatistics.getTurnover() + 1);
                break;
            default:
        }
    }

    public static void updateStatistic(TeamLiveStatistics teamLiveStatistics, LiveStatisticsEnum liveStatisticsEnum){
        switch (liveStatisticsEnum){
            case successful_effort:
                teamLiveStatistics.setSuccessful_effort(teamLiveStatistics.getSuccessful_effort() + 1);
                break;
            case total_effort:
                teamLiveStatistics.setTotal_effort(teamLiveStatistics.getTotal_effort() + 1);
                break;
            case successful_freethrow:
                teamLiveStatistics.setSuccessful_freethrow(teamLiveStatistics.getSuccessful_freethrow() + 1);
                break;
            case total_freethrow:
                teamLiveStatistics.setTotal_freethrow(teamLiveStatistics.getTotal_freethrow() + 1);
                break;
            case succesful_twopointer:
                teamLiveStatistics.setSuccesful_twopointer(teamLiveStatistics.getSuccesful_twopointer() + 1);
                break;
            case total_twopointer:
                teamLiveStatistics.setTotal_twopointer(teamLiveStatistics.getTotal_twopointer() + 1);
                break;
            case succesful_threepointer:
                teamLiveStatistics.setSuccesful_threepointer(teamLiveStatistics.getSuccesful_threepointer() + 1);
                break;
            case total_threepointer:
                teamLiveStatistics.setTotal_threepointer(teamLiveStatistics.getTotal_threepointer() + 1);
                break;
            case steal:
                teamLiveStatistics.setSteal(teamLiveStatistics.getSteal() + 1);
                break;
            case assist:
                teamLiveStatistics.setAssist(teamLiveStatistics.getAssist() + 1);
                break;
            case block:
                teamLiveStatistics.setBlock(teamLiveStatistics.getBlock() + 1);
                break;
            case rebound:
                teamLiveStatistics.setRebound(teamLiveStatistics.getRebound() + 1);
                break;
            case foul:
                teamLiveStatistics.setFoul(teamLiveStatistics.getFoul() + 1);
                break;
            case turnover:
                teamLiveStatistics.setTurnover(teamLiveStatistics.getTurnover() + 1);
                break;
            default:
        }
    }

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
