# CI/CD with GitHub Actions

## Do this first

Read the setup instructions for GitHub Codespaces in the [README](README.md) first. Ideally test them out briefly too, to make sure that the connection to your Seeker server is working and you have the right project key.

## Create a new Project in Seeker

To avoid confusion, create a new project in Seeker to keep your CI results separate from your Codespaces tests. If your Codespaces project had a project key of `JOHN-HIPPOTECH` you might want to create one with a project key of `JOHN-HIPPOTECH-CI`, for example.

## Create an Access Token in Seeker

In the Seeker Server Web UI:

1. Navigate to **Settings** --> **Access Token** and click **Generate new token**.
1. Select type: API.
1. Select your project from the drop-down list.
1. Select the following scopes: Manage Vulnerabilities, View Projects, View Reports, View Endpoints, View Vulnerabilities
1. Copy the generated access token

## Configure GitHub Actions for Seeker

GitHub Actions has a different set of secrets and configuration variables to GitHub Codespaces, so even if you have already run HippoTech with Seeker successfully you will need to set the Seeker Server URL and Seeker Project Key again, this time for GitHub Actions. You will also need to provide GitHub Actions with the Access Token you created during the previous step.

In your GitHub repository, navigate to Settings -> Secrets and variables -> Actions and click on **New repository secret**. 

1. Create a repository *secret* named `SEEKER_ACCESS_TOKEN`. Paste in the access token you copied from Seeker during the previous step.
1. Switch to the **Variables** tab and create a new repository variable (*not* a secret) named `SEEKER_SERVER_URL` and set this to the URL of your Seeker server.
1. Create a second repository variable named `SEEKER_PROJECT_KEY` and set this to the new Project Key you created earlier.

Your GitHub Actions pipeline is now configured to work with your Seeker server. The next time it runs the pipeline will download the Seeker agent and use it during the automated tests.

## Trigger the CI Workflow

The repository is configured to run the workflow in `.github/workflows/ci.yml` on every commit, but you can also trigger the workflow manually in GitHub. In your respository:

1. Click **Actions**.
1. Click on the **CI** workflow under **Actions**.
1. Click on **Run workflow** and run the workflow on the main branch.

