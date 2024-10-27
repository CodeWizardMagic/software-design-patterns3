public interface State {
    void play(Player player);
    void pause(Player player);
    void stop(Player player);
}

class PlayingState implements State {
    @Override
    public void play(Player player) {
        System.out.println("Player is already playing.");
    }

    @Override
    public void pause(Player player) {
        System.out.println("Pausing the player.");
        player.setState(new PausedState());
    }

    @Override
    public void stop(Player player) {
        System.out.println("Stopping the player.");
        player.setState(new StoppedState());
    }
}

class PausedState implements State {
    @Override
    public void play(Player player) {
        System.out.println("Resuming playback.");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(Player player) {
        System.out.println("Player is already paused.");
    }

    @Override
    public void stop(Player player) {
        System.out.println("Stopping the player from paused state.");
        player.setState(new StoppedState());
    }
}

class StoppedState implements State {
    @Override
    public void play(Player player) {
        System.out.println("Starting playback.");
        player.setState(new PlayingState());
    }

    @Override
    public void pause(Player player) {
        System.out.println("Player is stopped, can't pause.");
    }

    @Override
    public void stop(Player player) {
        System.out.println("Player is already stopped.");
    }
}

class Player {
    private State state;

    public Player() {
        this.state = new StoppedState(); // Начальное состояние
    }

    public void setState(State state) {
        this.state = state;
    }

    public void play() {
        state.play(this);
    }

    public void pause() {
        state.pause(this);
    }

    public void stop() {
        state.stop(this);
    }
}

public class StatePatternDemo {
    public static void main(String[] args) {
        Player player = new Player();
        
        player.play();   // Starting playback.
        player.pause();  // Pausing the player.
        player.play();   // Resuming playback.
        player.stop();   // Stopping the player.
        player.pause();  // Player is stopped, can't pause.
    }
}
