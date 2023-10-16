package com.andy.wordz.adapters.api;


import com.andy.wordz.domain.Player;

public record GuessRequest(Player player, String guess) {
}
