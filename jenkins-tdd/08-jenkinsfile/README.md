# Jenkins and GitHub Integration Guide

## Integrating Jenkins with Your GitHub Repository

### Create a Jenkins Project for Your Pipeline

- Open Jenkins in your browser and log in.
- Click `New Item`, name it `do400-pipelines-control`, select `Pipeline`, and click `OK`.

### Configure Build Triggers

- In the project configuration, click the `Build Triggers` tab.
- Select `GitHub hook trigger for GITScm polling`.

### Set Up Pipeline from SCM

- Go to the `Pipeline` tab.
- From the `Definition` dropdown, select `Pipeline script from SCM`.
- Choose `Git` as SCM and enter your GitHub repository URL.
- In the `Branch Specifier` field, enter `*/*` to include all branches.

### Save Your Pipeline Configuration

- Click `Save` to apply the changes.

## Setting Up GitHub to Notify Jenkins of New Pushes

### Generate a Personal Access Token on GitHub

- Navigate to GitHub settings → `Developer settings` → `Personal access tokens`.
- Click `Generate new token`, name it `Jenkins Integration`, select `admin:repo_hook`, and generate it.
- Copy the generated token for later use.

### Configure Jenkins with GitHub Server

- In Jenkins, go to `Manage Jenkins` → `Configure System`.
- Scroll to the GitHub section, click `Add GitHub Server`, then `GitHub Server`.
- Name it `GitHub`, add the generated token as `Secret text` under credentials, and select it.
- Click `Test connection` to ensure it works, then `Apply`.

### Enable Webhooks Automatically

- Check `Manage hooks` in the GitHub server configuration.
- Click `Advanced` and then `Re-register hooks for all jobs`.
- Save the configuration.

## Running and Observing Your Pipeline

### Execute Your Pipeline Manually

- Back in your project, click `Build Now` to start a pipeline execution manually.
- Observe the build progress and results in the `Stage View`.

### Automate Pipeline Execution on New GitHub Pushes

- With the webhook configured, any new push to the GitHub repository will trigger a new pipeline execution in Jenkins.
- Observe automatic builds initiated by GitHub pushes.

## Clean-Up

### Delete Your Jenkins Pipeline (Optional)

- In your Jenkins project, click `Delete Pipeline` and confirm to remove the pipeline project.
