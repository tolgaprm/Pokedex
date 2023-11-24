package com.prmto.poxedex.domain.model_generator

import com.prmto.poxedex.domain.model.StatType

fun statType(
    hpValue: Int,
    attackValue: Int,
    defenseValue: Int,
    specialAttackValue: Int,
    specialDefenseValue: Int,
    speedValue: Int
): Map<StatType, Int> {
    return mapOf(
        StatType.HP to hpValue,
        StatType.ATTACK to attackValue,
        StatType.DEFENSE to defenseValue,
        StatType.SPECIAL_ATTACK to specialAttackValue,
        StatType.SPECIAL_DEFENSE to specialDefenseValue,
        StatType.SPEED to speedValue
    )
}