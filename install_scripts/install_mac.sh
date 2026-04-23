#!/usr/bin/env bash
set -e

ENV_NAME=".venv"
KERNEL_NAME="ast-analyzer"
KERNEL_DISPLAY_NAME="Python (ast-analyzer)"

echo "Checking for Homebrew..."
if ! command -v brew >/dev/null 2>&1; then
  echo "Homebrew is not installed."
  echo "Install it from: https://brew.sh/"
  exit 1
fi

echo "Installing Python..."
brew install python

echo "Creating virtual environment..."
python3 -m venv "$ENV_NAME"

echo "Activating virtual environment..."
source "$ENV_NAME/bin/activate"

echo "Upgrading pip..."
python -m pip install --upgrade pip

echo "Installing Python packages..."
pip install jupyter ipykernel pandas javalang

echo "Registering Jupyter kernel..."
python -m ipykernel install --user --name "$KERNEL_NAME" --display-name "$KERNEL_DISPLAY_NAME"

echo
echo "Done."
echo "Activate the environment with:"
echo "source $ENV_NAME/bin/activate"