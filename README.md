# voip-demo

Demo for a Voip project.

Uses Clean architecture with MVVM as a presantion pattern. 
All UI is in fragments: `CallFragment` and `UserListFragment`. For every fragment there is a view model. Calling business logic is incapsulated in `CallInteractor`. It uses `mVoipGateway` that wraps work with actual Voip provider (which is curently Tokbox https://tokbox.com/). `CallInteractor` also uses `MicrophoneGateway` to turn microphone on and off. 

Getting users from database is done via `UserListInteractor` which also sorts users by online status. `UsersLocalRepository` incapsulates work with `UsersDao` which currently generates users that are randomly online or offline. When call button is clicked, `UserListViewModel` selects randomly an online user and opens `CallFragment`, in which the call happens.
