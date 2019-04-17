## How to do a github

#### Cloning

1. Download git from [here](https://git-scm.com/download "Git Download").
2. In your terminal/cmd, use `cd` to navigate to where you want to clone the directory.
3. Run the command `git clone https://github.com/fCherkasskiy/Karels-Adventure.git`. This will create a directory named after the repo where you currently are in the terminal.
4. Assuming everything worked, you should now have the repository cloned and working.

#### General Git Stuff

- Use `git pull` to update your local repository.
- If you have an empty directory, you cannot add it. If you must do so, just put an empty file.
- When you create a new file or directory, use the command `git add .` to make git track all of the untracked files.
- When you are ready, use `git commit -m "commit message"` to commit. If you forget the `-m` or the commit message, it will be tricky to fix, but not a big deal. Just close the terminal and try again. You could also just have an empty string `""` as the commit message if you like.
- Finally use `git push` to actually push your commits to GitHub website.

#### Gitignore
- The file ".gitignore" contains all the filetypes that Git will ignore. This is useful for compiled files and configuration files that really only just clutter things up and can confuse you.
- Add the filetype with an asterik before it to the ".gitignore" before you create the file.
  - Example: `*.docx`
