# **Git学习笔记**

## **git config配置**
#### （1）配置Git SSH Key：
在Git Bash中输入如下命令：
```
ssh-keygen -t rsa -C "xxx@xx.com"
```
一直回车，最终会在默认文件夹中（C:/UserDirectory/.ssh）创建两个文件：
```
C:/UserDirectory/.ssh
  |- id_rsa
  |- id_rsa.pub
```
在GitHub中配置SSH Key：<br/>
`GitHub主页` -> `Settings` -> `SSH and GPG keys` -> `New SSH key`<br/>
在`Title`框中输入Key的名称，`Key`中输入`id_rsa.pub`文件中的内容。<br/>
最后点击`Add SSH key`按钮即可。

#### （2）配置git的用户名和邮箱：
在Git Bash中输入如下命令：
```
git config --global user.name XXXXX
git config --global user.email XXXXX@XX.com
```
可以通过下面的代码查看git配置：
```
git config --list
```

## **Git命令**
#### 克隆一个项目到本地
```
git clone XXXXXXXXX
```

#### 查看git远程连接
```
git remote -v
```

#### 查看当前本地所有分支
```
git branch
```

#### 创建本地分支但不跳转到新分支
```
git branch XXX
```

#### 创建本地分支并跳转到新分支
```
git checkout -b XXX
```

#### 切换到本地分支
```
git checkout XXX
```

#### 将远程分支复制到本地
```
git checkout -b XXX AAA/BBB
# 上述代码将远程仓库中AAA连接中的BBB分支复制到本地，并将本地分支命名为XXX
```

#### 查看当前状态
```
git status
```

#### 添加本地修改
```
git add XXX.XXX
git add *
# 前一句是提交某个文件（某个文件夹）；后一句是将所有修改都进行添加
```

#### 暂存本地修改
```
git stash
# 将当前进行的修改进行暂存
git stash list
# 查看暂存记录列表
git stash pop
# 应用上一次暂存的代码
git stash clear
# 清空暂存栈
```

#### 将修改提交到本地暂存库
```
git commit -m "XXXXXX"
# XXXXXX是对本次提交的说明备注
```

#### 将本地暂存库中的内容推送到远程仓库
```
git push origin master
```

#### 将本地分支推送作为远程分支
```
git push origin AAA:BBB
# 将本地的AAA分支推送到远程仓库，作为远程仓库的BBB分支（如果没有则自动创建BBB分支）
```

#### 从远程仓库中拉取数据
```
git pull origin master
```

## **Git操作案例**
#### 需求：将分支wzy与master合并后提交
```git
# 查看所有分支及当前所在的分支
git branch
# 切换到master分支
git checkout master
# 拉取远程仓库中的最新更改
git pull
# 查看分支中尚未提交的部分
git status
# 切换到wzy分支
git checkout wzy
# 将wzy分支与master分支合并（在wzy分支合并，因此是合并到wzy分支）
git merge master
# 将更改进行添加
git add *
# 将更改提交到本地暂存库
git commit -m "aaaa"
# 推送到远程仓库，由于远程仓库中没有wzy分支，因此要加 -u
git push -u origin wzy
```