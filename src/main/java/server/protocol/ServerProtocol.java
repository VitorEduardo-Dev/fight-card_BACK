package server.protocol;

public class ServerProtocol {
    private static final int WAITING = 0;
    private static final int SENTSERVER = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMREF = 5;

    private int state = WAITING;
    private int currentRef = 0;

    private String[] clues = { "Database", "Security", "Assets", "Network", "Initialization" };
    private String[] answers = {
            "Database connection established successfully.",
            "Encryption protocols and security layers are now active.",
            "Global assets and internal dependencies loaded.",
            "Network latency optimized for regional access.",
            "Server is online and ready for incoming connections."
    };

    public String processInput(String theInput) {
        String theOutPut = null;

        if(state == WAITING) {
            theOutPut = "System Initialization Sequence started. Type 'proceed' to begin diagnostics.";
            state = SENTSERVER;
        } else if(state == SENTSERVER) {
            if(theInput.equalsIgnoreCase("proceed")) {
                theOutPut = "Checking module: [ " + clues[currentRef] + " ]. Confirm status request? (Type 'confirm')";
                state = SENTCLUE;
            } else {
                theOutPut = "Invalid command. Please type 'proceed' to continue system boot.";
            }
        } else if(state == SENTCLUE) {
            if(theInput.equalsIgnoreCase("confirm")) {
                theOutPut = "STATUS: " + answers[currentRef] + "Run next check? (y/n)";
                state = ANOTHER;
            } else {
                theOutPut = "Confirmation failed. Type 'confirm' to verify" + clues[currentRef] + " status.";
                state = SENTSERVER;
            }
        } else if(state == ANOTHER) {
            if(theInput.equalsIgnoreCase("y")) {
                if(currentRef == (clues.length - 1)) {
                    currentRef = 0;
                } else {
                    currentRef++;
                }
                theOutPut = "Initiating next module check: [ " + clues[currentRef] + " ]. Type 'confirm' to proceed.";
                state = SENTCLUE;
            } else {
                theOutPut = "Initialization sequence complete. System standby.";
                state = WAITING;
            }
        }
        return theOutPut;
    }
}
