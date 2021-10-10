package com.unitelmasapienza.asciiart.asciipanel;

/**
 * It is an interface that suggests that it seems to want to be a tile transformation contract in the application.
 * However, it is never implemented and the only two methods that use it are never called in the application.
 * 
 * @see com.unitelmasapienza.asciiart.asciipanel.AsciiPanel#withEachTile(TileTransformer) and {@link com.unitelmasapienza.asciiart.asciipanel.AsciiPanel#withEachTile(int, int, int, int, TileTransformer)}
 * 
 * @author Fulvio Zecchin
 *
 */
public interface TileTransformer {
	public void transformTile(int x, int y, AsciiCharacterData data);
}