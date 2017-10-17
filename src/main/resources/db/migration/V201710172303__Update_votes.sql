INSERT INTO VOTE(team_id, answer_id, player_id) SELECT team.id, answer.id, player.id from team JOIN answer on vote_name = 'OVER' JOIN player on first_name ='Chris' where team_name = 'Indiana Pacers';


update vote set answer_id = 2 where team_id = 5 and player_id = 16;
update vote set answer_id = 4 where team_id in (25, 30) and player_id = 16