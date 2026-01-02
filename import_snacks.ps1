$json = Get-Content f:\three\web\snack_shoop\snacks.json -Raw | ConvertFrom-Json

foreach ($snack in $json) {
    $body = $snack | ConvertTo-Json
    try {
        $result = Invoke-RestMethod -Uri "http://localhost:8080/api/admin/snack/add" -Method Post -ContentType "application/json" -Body $body
        Write-Host "Added $($snack.name): Success"
    } catch {
        Write-Host "Error adding $($snack.name): $_"
    }
}
