def boar_brawl(player_score, opponent_score):
    """Return the points scored by rolling 0 dice according to Boar Brawl.

    player_score:     The total score of the current player.
    opponent_score:   The total score of the other player.

    """
    # BEGIN PROBLEM 2
    "*** YOUR CODE HERE ***"
    if player_score >10:
        cps =player_score %10
    else:
        cps =player_score
    if player_score <10:
        ops = 0
    else:
        ops=player_score//10%10
    if 3*abs(ops-cps) <=1:
        return 1
    else:
        return 3*abs(ops-cps)
    # END PROBLEM 2
print(boar_brawl(21,46))