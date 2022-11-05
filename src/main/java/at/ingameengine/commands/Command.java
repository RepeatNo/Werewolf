package at.ingameengine.commands;

public interface Command {
   void accept(GameStateVisitor commandInspector);
}
