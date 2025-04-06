# Script pour organiser le projet en déplaçant les fichiers frontend

# Dossier source
$sourceDir = "c:\Users\Papa Omar\Desktop\Projet Seck\GestionBibliotheque"
$frontendDir = "c:\Users\Papa Omar\Desktop\Projet Seck\GestionBibliotheque\frontend"

# Liste des fichiers à déplacer
$filesToMove = @(
    "install-frontend.ps1"
)

# Déplacer les fichiers
foreach ($file in $filesToMove) {
    $sourcePath = Join-Path $sourceDir $file
    $destinationPath = Join-Path $frontendDir $file
    
    if (Test-Path $sourcePath) {
        Write-Host "Déplacement de $file vers le dossier frontend..."
        Move-Item -Path $sourcePath -Destination $destinationPath -Force
    }
}

# Supprimer les fichiers de crash Java qui ne sont pas nécessaires
$hsErrFiles = Get-ChildItem $sourceDir -Filter "hs_err_pid*.log"
$replayFiles = Get-ChildItem $sourceDir -Filter "replay_pid*.log"

foreach ($file in @($hsErrFiles + $replayFiles)) {
    Write-Host "Suppression de $($file.Name)..."
    Remove-Item -Path $file.FullName -Force
}

Write-Host "Organisation du projet terminée !"
