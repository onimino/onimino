package org.zeromeaner.game.knet;

import java.util.ArrayList;
import java.util.Properties;

import org.zeromeaner.game.component.Block;
import org.zeromeaner.game.component.Field;
import org.zeromeaner.game.component.Piece;
import org.zeromeaner.game.component.RuleOptions;
import org.zeromeaner.game.component.Statistics;
import org.zeromeaner.game.knet.obj.PieceHold;
import org.zeromeaner.game.knet.obj.PieceMovement;
import org.zeromeaner.game.knet.ser.BlockSerializer;
import org.zeromeaner.game.knet.ser.FieldSerializer;
import org.zeromeaner.game.knet.ser.PieceSerializer;
import org.zeromeaner.game.knet.ser.PropertiesSerializer;
import org.zeromeaner.game.knet.ser.StatisticsSerializer;
import org.zeromeaner.game.knet.srv.KNetChannelInfo;
import org.zeromeaner.game.knet.srv.KNetPlayerInfo;
import org.zeromeaner.util.CustomProperties;

import com.esotericsoftware.kryo.Kryo;

public class KNetKryo {
	public static void configure(Kryo kryo) {
		kryo.register(KNetEvent.class);
		kryo.register(KNetEventSource.class);
		kryo.register(KNetChannelInfo.class);
		kryo.register(KNetChannelInfo[].class);
		kryo.register(KNetPlayerInfo.class);
		kryo.register(Field.class, new FieldSerializer());
		kryo.register(Block[][].class);
		kryo.register(Block[].class);
		kryo.register(Block.class, new BlockSerializer());
		kryo.register(boolean[].class);
		kryo.register(ArrayList.class);
		kryo.register(Piece.class, new PieceSerializer());
		kryo.register(int[][].class);
		kryo.register(int[].class);
		kryo.register(Piece[].class);
		kryo.register(PieceHold.class);
		kryo.register(PieceMovement.class);
		kryo.register(Properties.class, new PropertiesSerializer());
		kryo.register(CustomProperties.class, new PropertiesSerializer());
		kryo.register(Statistics.class, new StatisticsSerializer());
		kryo.register(RuleOptions.class, new com.esotericsoftware.kryo.serializers.FieldSerializer<RuleOptions>(kryo, RuleOptions.class));
	}
}
