package org.bukkit.event.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Thrown when a player executes a command that is not defined
 */
@NullMarked
public class UnknownCommandEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final CommandSender sender;
    private final String commandLine;
    private @Nullable Component message;

    @ApiStatus.Internal
    public UnknownCommandEvent(final CommandSender sender, final String commandLine, final @Nullable Component message) {
        super(false);
        this.sender = sender;
        this.commandLine = commandLine;
        this.message = message;
    }

    /**
     * Gets the CommandSender or ConsoleCommandSender
     *
     * @return Sender of the command
     */
    public CommandSender getSender() {
        return this.sender;
    }

    /**
     * Gets the command that was sent
     *
     * @return Command sent
     */
    public String getCommandLine() {
        return this.commandLine;
    }

    /**
     * Gets message that will be returned
     *
     * @return Unknown command message
     * @deprecated use {@link #message()}
     */
    @Deprecated
    public @Nullable String getMessage() {
        return this.message == null ? null : LegacyComponentSerializer.legacySection().serialize(this.message);
    }

    /**
     * Sets message that will be returned
     * <p>
     * Set to {@code null} to avoid any message being sent
     *
     * @param message the message to be returned, or {@code null}
     * @deprecated use {@link #message(Component)}
     */
    @Deprecated
    public void setMessage(@Nullable String message) {
        this.message(message == null ? null : LegacyComponentSerializer.legacySection().deserialize(message));
    }

    /**
     * Gets message that will be returned
     *
     * @return Unknown command message
     */
    @Contract(pure = true)
    public @Nullable Component message() {
        return this.message;
    }

    /**
     * Sets message that will be returned
     * <p>
     * Set to {@code null} to avoid any message being sent
     *
     * @param message the message to be returned, or {@code null}
     */
    public void message(@Nullable Component message) {
        this.message = message;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}

