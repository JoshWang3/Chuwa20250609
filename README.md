# Chuwa 06/09/2025



## First time configure

---

### 1. Clone this repo:

open your teminal, run below command.

```bash
cd <your_work_dir>
git clone git@github.com:JoshWang3/Chuwa20250609.git
```



### 2. Send your github email address to me on Teams and I will add you to this repo



### 3. Create your <u>*own main branches*</u>

- Use this branch as your <u>*own main branch*</u>, you should always raise pr and merge to this branch

```bash
git branch firstName_lastName/main
git checkout firstName_lastName/main

git push origin firstName_lastName/main
```









## Submit your assigments

---

### 3. Create a new feature branch <u>*each time*</u>

```bash
example: 
HW1:
git checkout -b firstName_lastName/hw1

### Writing your hw

git add .
git commit -m "commit_message"
git push origin firstName_lastName/hw1
```



### 4. Push your local branch to Github

```bash
git push origin firstName_lastName/hw1
```



### 5. Raise PR

Open github, **Compare and Pull** or **New pull request**

- **to `firstName_lastName/main`**
- **from `firstName_lastName/hw1`**



### *You should always raise your PRs to <u>your own firstName_lastName/main branch</u>, **Please don't raise your PR to main branch***
