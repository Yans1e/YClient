package de.yansie.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import de.yansie.Variables;

public class RPC {
    private final DiscordRPC lib = DiscordRPC.INSTANCE;
    private final String largeImageKey$final;
    private final String largeImageText$final;
    private String largeImageKey;
    private String largeImageText;
    private RPCCache rpcCash;
    private RPCParty rpcParty;

    private boolean party = false;
    private static boolean created = false;
    private long start;
    private Thread worker;




    public RPC(String largeImageKey, String largeImageText){
        this.largeImageKey = largeImageKey;
        this.largeImageText = largeImageText;
        this.largeImageKey$final = largeImageKey;
        this.largeImageText$final = largeImageText;
        this.start = -1;
    }

    public RPC create(){
        String applicationID = "1253088903800557630";
        String steamID = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = user -> System.out.println("Discord RPC - Ready");
        lib.Discord_Initialize(applicationID,handlers,true,steamID);
        worker = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"RPC-Callback-Handler");
        worker.start();
        return this;
    }

    public void destroy(){
        worker.interrupt();
        worker = null;
        party = false;
        rpcCash = null;
        rpcParty = null;
        start = -1;
        lib.Discord_Shutdown();
    }

    public void update(String details, String state, String smallImgeKey, String smallImageText){
        DiscordRichPresence presence = new DiscordRichPresence();
        if (start == -1)
            start = System.currentTimeMillis() / 1000;
        presence.startTimestamp = start;
        presence.details =details;
        presence.state = state;
        presence.largeImageKey = largeImageKey;
        presence.largeImageText = largeImageText;
        presence.smallImageText = smallImageText;
        presence.smallImageKey = smallImgeKey;
        if (party){
            presence.partyId = rpcParty.getId();
            presence.partySize = rpcParty.getSize();
            presence.partyMax = rpcParty.getMax();
            presence.joinSecret = rpcParty.getJoinsecret();
        }
        lib.Discord_UpdatePresence(presence);
        rpcCash = new RPCCache(details,state,smallImgeKey,smallImageText);
    }


    public RPC setupParty(int max){
        return setupParty(new RPCParty(max));
    }
    public RPC setupParty(RPCParty rpcParty){
        this.rpcParty = rpcParty;
        Variables.rpcParty = rpcParty;
        party = true;
        return this;
    }

    public void destroyParty(){
        this.rpcParty = null;
        Variables.rpcParty = null;
        party = false;

    }
    public RPC setLargeImage(String key){
        this.largeImageKey = key;
        return this;
    }
    public RPC setLargeImageText(String text){
        this.largeImageText = text;
        return this;
    }

    public RPC resetLargeImageText(){
        this.largeImageText = largeImageText$final;
        return this;
    }
    public RPC resetLargeImageKey(){
        this.largeImageKey = largeImageKey$final;
        return this;
    }

    public static RPC instance(){
        if (!created){
            created = true;
            return Variables.rpc.create();
        }
        return Variables.rpc;
    }
}
