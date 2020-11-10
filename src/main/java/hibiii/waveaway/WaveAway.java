package hibiii.waveaway;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Hand;

public class WaveAway implements ModInitializer {
	
	private static KeyBinding bindWaveMainHand;
	private static KeyBinding bindWaveOffHand;
	
	@Override
	public void onInitialize() {

		// Init the keybinds
		bindWaveMainHand = new KeyBinding (
				"key.waveaway.waveMainHand", // Translation key
				InputUtil.Type.KEYSYM,       // Type
				GLFW.GLFW_KEY_F7,            // Default
				"key.categories.multiplayer" // Category translation key
		);
		bindWaveOffHand = new KeyBinding (
				"key.waveaway.waveOffHand",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_F6,
				"key.categories.multiplayer");
		
		// Register the keybinds for a purpose
		KeyBindingHelper.registerKeyBinding(bindWaveMainHand);
		KeyBindingHelper.registerKeyBinding(bindWaveOffHand);
		
		// Register callback to check for key presses every client tick
		// (may be faster than the server's world tick)
		ClientTickCallback.EVENT.register(client -> {
			while(bindWaveMainHand.wasPressed())
				MinecraftClient.getInstance().player.swingHand(Hand.MAIN_HAND);
			while(bindWaveOffHand.wasPressed())
				MinecraftClient.getInstance().player.swingHand(Hand.OFF_HAND);
		});
	}	
}
