This system is basically a secondary way of processing events and has been made for when I am developing dedicated minigames/modes not intended to be compatible
with most mods.

It creates a consistent queue based on priority's provided by listeners and then fires them from lowest priority to highest.

This is not intended for use on mods meant for the wider ecosystem and is instead for when I want finer control over certain things.

The reason for such strict access is that when I make these dedicated minigames/modes I always attempt to keep it as open as possible to addons and such so the
strict access controlling is to make sure either I or any people making addons cannot break something or accidentally access something outside of queue time.