package de.maxhenkel.replayvoicechat;

import de.maxhenkel.replayvoicechat.recording.VoicechatRecorder;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatClientApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.*;
import de.maxhenkel.voicechat.plugins.impl.VoicechatClientApiImpl;

public class ReplayVoicechatPlugin implements VoicechatPlugin {

    public static VoicechatClientApi CLIENT_API = new VoicechatClientApiImpl();

    @Override
    public String getPluginId() {
        return ReplayVoicechat.MOD_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {

    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(ClientVoicechatConnectionEvent.class, this::onConnection);
        registration.registerEvent(ClientReceiveSoundEvent.EntitySound.class, VoicechatRecorder::onEntitySound);
        registration.registerEvent(ClientReceiveSoundEvent.LocationalSound.class, VoicechatRecorder::onLocationalSound);
        registration.registerEvent(ClientReceiveSoundEvent.StaticSound.class, VoicechatRecorder::onStaticSound);
        registration.registerEvent(ClientSoundEvent.class, VoicechatRecorder::onSound);
    }

    private void onConnection(ClientVoicechatConnectionEvent event) {
        CLIENT_API = event.getVoicechat();
    }

}
