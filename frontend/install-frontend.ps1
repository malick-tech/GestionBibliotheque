# Script d'installation du frontend
Write-Host "Installation des dépendances Angular..."

# Changer de répertoire vers frontend
Set-Location "frontend"

# Installer Angular Material
Write-Host "Installation de Angular Material..."
npm install @angular/material @angular/cdk

# Installer Angular Animations
Write-Host "Installation de Angular Animations..."
npm install @angular/animations

# Installer Angular PWA
Write-Host "Installation de Angular PWA..."
npm install @angular/pwa

# Installer les thèmes de Material
Write-Host "Installation des thèmes Material..."
npm install @angular/material @angular/cdk

Write-Host "Installation terminée !"
