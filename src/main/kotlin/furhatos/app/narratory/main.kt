package furhatos.app.narratory

import furhatos.app.narratory.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class NarratorySkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
