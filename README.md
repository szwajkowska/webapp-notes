
[![Build Status](https://travis-ci.org/szwajkowska/webapp-notes.svg?branch=master)](https://travis-ci.org/szwajkowska/webapp-notes)

# Webapp-notes

Project is a simple web application, which allows to storage and manage notes. 
User can create an account, log in, save and delete messages.
To storage data I have used MongoDB database. 

<b>Code example</b>

    @PostMapping
    String createNewUser(@Valid @ModelAttribute UserModel userModel, BindingResult result) {
        userValidator.validate(userModel, result);
        if (result.hasErrors()) {
            return "signIn";
        }
        userList.addUser(userModel.getUsername(), userModel.getPassword());
        return "redirect:/login?signedin";
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        UserModel userModel = (UserModel) target;
        String password = userModel.getPassword();
        String confPassword = userModel.getConfPassword();
        if (!password.equals(confPassword)) {
            errors.rejectValue("password", "userModel.password.missMatch");
        }
    }
    
## Tests

Each controller is tested by JUnit tests. I have used Travis CI to run all tests at each commit. 
To execute tests run command:

    ./gradlew clean test
  
 <b>Test code example</b>
 
    @Test
    public void shouldAddNote() throws Exception {
        Principal principal = () -> "name";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/main")
                        .principal(principal)
                        .param("body", "body")
                        .with(SecurityMockMvcRequestPostProcessors.user("admin")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Assertions.assertThat(notesRepositoryMongo.count()).isEqualTo(1);
        Assertions.assertThat(notesRepositoryMongo.findByUsername("name").get(0).getBody()).isEqualTo("body");
    }
    
    
