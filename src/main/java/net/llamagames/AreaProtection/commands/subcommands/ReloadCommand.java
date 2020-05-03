package net.llamagames.AreaProtection.commands.subcommands;

import cn.nukkit.command.CommandSender;
import net.llamagames.AreaProtection.AreaProtection;
import net.llamagames.AreaProtection.utils.Language;

// idea from: Extollite (#4)

public class ReloadCommand extends SubCommand {

    public ReloadCommand() {
        super("reload");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        AreaProtection.getInstance().loadAreas();
        sender.sendMessage(Language.get("ap-reloaded"));
    }
}
