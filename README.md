# WeBall Statistics

<h4>The WeBall Statistics application is a league statistics application for basketball, which was created as part of the course "Apps development for Mobile Devices" (University of Macedonia - Applied Informatics, academic year 2021-2022, 6th semester).</h4>
<h4>Part of the course, was to get organized into groups of 10 people. Our team (#Team 2) consists of the following students alphabetically:</h4>
<ul>
  <li><b><i>Ampatzidou Elisavet</i></b></li>
  <li><b><i>Charakopoulos Minas - Theodoros</i></b></li>
  <li><b><i>Dasyra Evmorfia - Elpida </i></b></li>
  <li><b><i>Iordanou Sofia</i></b></li>
  <li><b><i>Lougaris Dionisis </i></b></li>
  <li><b><i>Lousta Aravella</i></b></li>
  <li><b><i>Machairas Panagiotis</i></b></li>
  <li><b><i>Ouzounidis Kyriakos</i></b></li>
  <li><b><i>Pepa Leonard</i></b></li>
  <li><b><i>Stefou George-John</i></b></li>
</ul>

<h4>Video presentation of the app on YouTube: <a href="https://www.youtube.com/watch?v=ouzMwkUCQ-s&list=LL&index=12"><b><i>presentation video</i></b><a/></h4>
<h4>Visit the other repo, with the back-end of our application: <a href="https://github.com/uom-android-team2/WeBall_Statistics-Backend"><b><i>back-end</i></b><a/></h4>

<h2>R1 &nbsp;&nbsp; Display matches - Admin Login - Guest Starting Page</h2>
<div float="left">
  <img src="screenshots/R1/start-page.png" height="450" />
  <img src="screenshots/R1/admin-login.png" height="450" />
  <img src="screenshots/R1/upcoming-matches-admin-view-before-start.png" height="450" />
</div>
<br>
<p><b><i>Requirement 1</i></b> is handled by the administrator (admin) and it concerns the match selection, and manage the card of the selected match. This includes the appearance of players and the corresponding ones logos. First, the administrator must enter the username and password in the form is requested, after choosing the button “LOGIN AS ADMIN". The admin account can created by the backend website through it register. It then goes to the “Matches” screen, where there are three columns with the corresponding matches (Previous Matches, Live Matches, Upcoming Matches). From there the administrator can choose a match and edit the tab of. More specifically, by pressing it arrow a popup appears menu, which includes the players of the groups. With the edit button, is transferred to the “Welcome Admin” screen, where it is match management (see R2). <br>
Note: The edit option concerns ONLY the live and upcoming matches, not the completed.</p>

<h2>R2 &nbsp;&nbsp; Match management by Admin</h2>
<div float="left">
  <img src="screenshots/R2/admin-live-match-panel.png" height="450" />
  <img src="screenshots/R2/live-match-admin.png" height="450" />
  <img src="screenshots/R2/pop-add-points.png" height="450" />
</div>
<br>
<p><b><i>Requirement 2</i></b> constitutes the next possibility of the administrator (admin), which includes all functions related to managing a match (Shoot with
type and result, Rebound or Assist or Block, Steal, Turnover and Foul). Necessary condition for starting the game, but also to activate the buttons, it is to click the button "Start". After it's click, the same button turns into “End”, through which the match could be terminated. In addition, the administrator can stop the timer and start it (from where you stopped it) via the “Pause” button, the which turns into “Continue” when pressed, and vice versa. Still, for registering any action, it is necessary, the administrator to follow a series of steps. Initially, the administrator during his navigation on this screen, sees the home team selected (by default). To if he can change team, he uses the Banner of which located at the top as an image. Once he selects any from the 2 teams, the key players of the selected team, appear at the bottom of the screen. In the same way that the team is selected, a player can also be selected, that is, by clicking on his image. <br>
Note: The match does not end when "Done" is pressed. Only will paused and the admin will moved back to all matches.</p>

<h2>R3 &nbsp;&nbsp; Recording statistics</h2>
<p>This particular requirement deals with calculating stats for players and the groups, when the administrator performs a corresponding action. These actions include the basic events that occur in a match basketball skills such as free throws and attempts (two-pointers, three-pointers). In addition, assists, rebounds, steals, cuts, fouls are also recorded and mistakes. The specific statistics are recorded in the database at the moment which the administrator presses a similar button from its graphical interface which is fragment_admins_view. Statistics recording can begin the moment a match is live. For something like this to happen, it is necessary o
administrator to press the start button from its graphical interface. On the contrary, if the administrator seeks to increase statistics for a player, team respectively buttons will be disabled. Beyond the simple recording of statistics n application modifies the above data into a more logical form for the user. Specifically, in the frontend, a percentage calculation is also carried out through a condition. To if the application succeeds in the specific process, it stores its data in classes statistics for players and teams from the database.</p>

<h2>R4 &nbsp;&nbsp; Watch the progress of the Live Match</h2>
<div float="left">
  <img src="screenshots/R4/live-progress-1.png" height="450" />
  <img src="screenshots/R4/live-progress-2.png" height="450" />
  <img src="screenshots/R4/live-comments-1.png" height="450" />
</div>
<br>
<p>The purpose of implementing <b><i>requirement 4</i></b> is that users as well as match administrators (admins) can watch the progression of a live match in real time, through verbal descriptions and comments for more details. In order to watch the progress of a live match, one must first choose one, from the list of live matches (Live matches), by clicking anywhere on its layout. Thus, it will be transferred to a new screen, where the 2 teams, the current score, the match week, as well as the current minutes of the match, are shown above. Below, is a menu available where gives the opportunity to watch the match through brief descriptions by selecting the "Progress" tab, as well as through comments and more detailed descriptions by pressing the "Comments" tab. He can also see the statistics of the teams and the respective players by going to the "Statistics" tab (R5). <br><br>
About the <b><i>"Progress"</i></b> tab, short descriptions are displayed for actions registered by the admin and performed in the match such as the following: </p>
<ul>
  <li>Start, complete, pause and resume of the match.</li>
  <li>Successful freethrow, 2-point and 3-point</li>
  <li>Rebound</li>
  <li>Steal</li>
  <li>The name of the player who made the Assist in parentheses (only for 2 points or 3 points).</li>
  <li>Block</li>
  <li>Foul</li>
  <li>Turnover</li>
  <li>Player Substitution</li>
</ul>
<p>About the <b><i>"Comments"</i></b> tab, all actions previously mentioned in the “Progress” tab are described in the form of comments with the extra addition of the display of missed free throws, 2-points and 3-points.</p>
<p>Left-aligned descriptions refer to the home team, right-aligned to the away team and in the center there are descriptions generally related to the flow of the match without specifically addressing any team.</p>
<p><b>Note: </b> <b><i>All descriptions are realtime and no refresh is required to display the newest events. Each new action will appear on top, first, at the beginning. Also, the score and the time shown in the header of the match are realtime and directly obey the actions of the admin.</i></b></p>
<h4>For the implementation of the real time data, the possibility of the real time database offered by the <a href="https://firebase.google.com/">Firebase</a> platform was utilized.</h4>

<h2>R5 &nbsp;&nbsp; Watch the Statistics of the Live Match (Both for Teams and Players)</h2>
<div float="left">
  <img src="screenshots/R5/live-team-statistics.png" height="450" />
  <img src="screenshots/R5/live-players-statistics-1.png" height="450" />
  <img src="screenshots/R5/live-players-statistics-2.png" height="450" />
</div>
<br>
<p><b><i>Requirement 5</i></b> will give the opportunity to the user to watch live stats of a match as it is live. The user, having selected a live match, has the possibility to see the relevant statistics of the teams from the last column of the "Live Game" screen. Here again the elements on the left side of the screen represent the home team, while the elements on the right represent the away team. In addition, the bars visually show the statistics of the teams by category <i> (Successful effort, Total effort, Successful freethrow, Total freethrow, Successful twopointer, Total twopointer, Successful threepointer, Total threepointer, Steal,
Assist, Block, Rebound, Foul, Turnover)</i>, while the number above each bar indicates the total number of attempts, successful shots, etc. The “View Players Live Statistics” button at the bottom of the screen, refers the user to the individual player statistics by category again. The team's overall data (e.g. total effort) is displayed on the right and the number representing each player on the left. The bar again indicates the corresponding percentage. Additionally, the user can select the player from the bottom of the screen or change team from the pop-up menu on the top left of the screen.</p>
<p><b>Note: </b> <b><i>Firebase was used to record and store the data, so that the comments of each match are always updated, without the need for further action by the user (e.g. refreshing the page).</i></b></p>

<h2>R6 &nbsp;&nbsp; Statistics of completed matches</h2>
<div float="left">
  <img src="screenshots/R6/completed-match-statistics-1.png" height="450" />
  <img src="screenshots/R6/completed-match-statistics-2.png" height="450" />
  <img src="screenshots/R6/completed-match-statistics-3.png" height="450" />
</div>
<br>
<p><b><i>Requirement 6</i></b> will offer the user the option to view the stats of a completed match. From the user's home match screen and the “Previous Matches” column, the user can select a completed match to view its statistics. In more detail, after selecting one, the individual statistics of the five most efficient players of each team are displayed, and below, a list of the leading players in the specific statistics for the selected match. The following are the total statistics of the two teams per category <i>(Team Efficiency, 3 Pointers Made, 2 Pointers Made, Total Freethrows, Team Assists, Total Fouls.
10)</i>. The left side is for the home team, while the right side is for the away team.</p>

<h2>R7 &nbsp;&nbsp; League Scoreboard</h2>
<div float="left">
  <img src="screenshots/R7/score-board.png" height="450" />
</div>
<br>
<p>In <b><i>requirement 7</i></b>, the scores of all the teams will be presented, after they have completed the match in which they are participating. More specifically, the user goes to the scoreboard screen from the "CHAMPIONSHIP LEADERBOARD" button located on the home screen. This screen shows the teams sorted according to their score (PTS). To the left of the columns is the position number of each team (#), followed by the team name, total games played so far (GM), wins (W) and losses (L) respectively and finally the points scored have collected (PTS). Points are calculated as the sum of wins times 2 and losses. Additionally, there are two buttons on this screen. The “STATISTICS” button, which leads to
team and player statistics, and the “TOP 5” button, which leads to the best 5 players (regardless of team).</p>

<h2>R8 &nbsp;&nbsp; Overall team stats</h2>
<div float="left">
  <img src="screenshots/R8/team-stats-all-time.png" height="450" />
</div>
<br>
<p>In <b><i>requirement 8</i></b>, the total statistics of the teams will be displayed. The user from the "STATISTICS" button of requirement 7 goes to the "Championship Statistics" screen, which has two tabs. The second tab “TEAM STATS” (related to R8), contains four tables per category. In each table the five teams with the highest percentages per category <i>(points per game, assists per game, rebounds per game, blocks per game, up to the given moment in the league)</i> are shown. For example, in “Points Per Game”, the user can see the top five teams that collected the most points per game.</p>

<h2>R9 &nbsp;&nbsp; Personal player stats</h2>
<div float="left">
  <img src="screenshots/R9/top-players-all-time.png" height="450" />
</div>
<br>
<p>In <b><i>requirement 9</i></b>, the personal statistics of the players throughout the league are displayed. The "STATISTICS" button of requirement 7 navigates the user to the "Championship Statistics" screen, which has two tabs. The first tab on the left (PLAYERS STATS) corresponds to requirement 9 and concerns the statistics of the players by category <i>(points per game, assists per game, rebounds per game, blocks per game, fouls per game)</i>, i.e. how many points, rebounds, assists, etc. collected by the respective player per game (up to the given moment in the league). In each category, the 5 players who collected the highest percentages per category, in the matches they have participated in so far, are displayed. These statistics are updated as the match progresses.</p>

<h2>R10 &nbsp;&nbsp; Top-5 players for each week</h2>
<div float="left">
  <img src="screenshots/R10/top5-per-week.png" height="450" />
</div>
<br>
<p>In <b><i>requirement 10</i></b>, the best 5 of the Competition are calculated based on the evaluation system (Efficiency meter). By pressing the button "TOP 5" the user enters the page and displays the 5 best players of the matchday (last matchday / current week), according to the position in which they play. For the selection of the top five, the statistics of all players who participated in the completed matches of the current week are gathered, the efficiency index of each player is calculated and then these indices are compared between players of the same position. The player with the greatest efficiency emerges as the best of the week for the position he plays. In addition, the data of the 5 best players is displayed in a frame with a background color that dominates the team they play.</p>

# Prerequisites
<ul>
  <li>Android Studio</li>
  <li>XAMPP Control Panel</li>
  <li>An emulator installed e.g. Nexus 5 API 30, Pixel 3 XL API 29</li>
  <li>Internet Connection</li>
</ul>

# Local Installation
<h4>For the correct use of the application, the following actions are required:</h4>

```
Run at first the back-end:
git clone https://github.com/uom-android-team2/WeBall_Statistics-Backend.git or download the zip from github and extract it
Store or move the root folder WeBall_Statistics-Backend(-master) in <PATH>\xampp\htdocs folder
Open XAMPP Control Panel and start Apache and MySQL servers
Visit from your browser http://localhost/WeBall_Statistics-Backend/index/ then register or login and follow the manual

Now, for the Mobile Application:
git clone https://github.com/uom-android-team2/WeBall_Statistics.git or download the zip from github and extract it
Store or move the root folder WeBall_Statistics(-main) in <PATH>\AndroidStudioProjects\
Open Android Studio and the app root folder.
Config the App:
public static final String IP = <YOUR_IP>  --> (java/uom/team2/weball_statistics/configuration/Config.java)
<domain includeSubdomains="true"><YOUR_IP></domain>  --> (res/xml/network_security_config.xml)
Start any emulator, and then you are ready to launch the app!
```

<h4>Note: Maybe you will see already data for live matches because of the real time cloud service was used, firebase real-time database!</h4>
