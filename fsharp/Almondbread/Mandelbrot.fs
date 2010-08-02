open System
open System.Numerics
open Microsoft.FSharp.Math

let width = 80
let height = 20

let escapeTime c = 
    let rec escapeTime' = fun c z step -> 
        if step > 256 || Complex.Abs z > 4.0 
        then step 
        else escapeTime' c (z*z + c) (step + 1)
    escapeTime' c Complex.Zero 0

let eachPoint width height f = 
    let rs = 3.0 / (float width)
    let is = 2.0 / (float height)
    for y in 0..height do
        for x in 0..width do
            f x y (escapeTime (new Complex(-2.0 + rs * float x, -1.0 + is * float y)))

eachPoint width height (fun x y v -> 
if x = 0 then printfn ""
printf(if v < 255 then "-" else "#"))
