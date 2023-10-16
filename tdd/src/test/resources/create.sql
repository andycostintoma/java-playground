CREATE TABLE IF NOT EXISTS word
(
    word_number integer NOT NULL PRIMARY KEY,
    word        char(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS game
(
    player_name    varchar(20) NOT NULL,
    word           char(5)     NOT NULL,
    attempt_number integer     NOT NULL,
    is_game_over   boolean DEFAULT FALSE
);

INSERT INTO word (word_number, word)
VALUES (1, 'ARISE'),
       (2, 'SHINE'),
       (3, 'LIGHT'),
       (4, 'SLEEP'),
       (5, 'BEARS'),
       (6, 'GREET'),
       (7, 'GRATE')
ON CONFLICT DO NOTHING;



