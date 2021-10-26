package org.quantumclient.banana.utilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class PlayerUtils implements MinecraftInterface {

    public enum FacingDirection
    {
        North,
        South,
        East,
        West,
    }

    public static FacingDirection GetFacing()
    {
        switch (MathHelper.floor((double) (mc.player.getYaw() * 8.0F / 360.0F) + 0.5D) & 7)
        {
            case 0:
            case 1:
                return FacingDirection.South;
            case 2:
            case 3:
                return FacingDirection.West;
            case 4:
            case 5:
                return FacingDirection.North;
            case 6:
            case 7:
                return FacingDirection.East;
            case 8:
        }
        return FacingDirection.North;
    }

    public enum highways {
        XP, XN, ZP, ZN, XPZP, XNZP, XPZN, XNZN
    }

    public static highways determineHighway() {
        PlayerEntity player = mc.player;
        highways highwayNum = highways.XP;
        if (player.getX() >= 100) {
            if (player.getZ() >= -5 && player.getZ() <= 5) {
                //+X highway
                highwayNum = highways.XP;
            }
            else if (player.getZ() - player.getX() >= -50 && player.getZ() - player.getX() <= 50) {
                //+X+Z highway
                highwayNum = highways.XPZP;
            }
            else if (player.getZ() + player.getX() >= -50 && player.getZ() + player.getX() <= 50) {
                //+X-Z highway
                highwayNum = highways.XPZN;
            }
        }
        else if (player.getX() <= -100) {
            if (player.getZ() >= -5 && player.getZ() <= 5) {
                //-X highway
                highwayNum = highways.XN;
            }
            else if (player.getX() + player.getZ() >= -50 && player.getX() + player.getZ() <= 50) {
                //-X+Z highway
                highwayNum = highways.XNZP;
            }
            else if (player.getZ() <= player.getX() + 100 && player.getZ() >= player.getX() - 100) {
                //-X-Z highway
                highwayNum = highways.XNZN;
            }
        }
        else if (player.getZ() >= 100) {
            if (player.getX() >= -5 && player.getX() <= 5) {
                //+Z highway
                highwayNum = highways.ZP;
            }
        }
        else if (player.getZ() <= -100) {
            if (player.getX() >= -5 && player.getX() <= 5) {
                //-Z highway
                highwayNum = highways.ZN;
            }
        }
        return highwayNum;
    }


}
