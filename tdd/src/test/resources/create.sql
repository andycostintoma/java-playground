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