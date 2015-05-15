package net.sourceforge.peers.media;

import net.sourceforge.peers.rtp.RtpPacket;

public interface DTMFReader {
    public void dtmfReceived(RtpPacket rtpPacket);
}
