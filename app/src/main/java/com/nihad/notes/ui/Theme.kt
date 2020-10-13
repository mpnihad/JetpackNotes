 package com.nihad.notes.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.graphics.Color
import com.nihad.notes.ui.utils.SysUiController


private val LightColorPalette = TipcalcColorPalette(
    primary = violet200,
    primaryVariant = purple700,
    secondary = orange200,
    background = white,
    backgroundreverse = black,
    brand = Shadow5,
    bgcolor = bgwhite,
    uiBackground = Neutral0,
    uiBorder = Neutral4,
    uiFloated = FunctionalGrey,
    textPrimary = Shadow1,
    textSecondary = Neutral7,
    textHelp = Neutral6,
    textInteractive = Neutral0,
    textLink = Ocean11,
    iconSecondary = Neutral7,
    iconInteractive = Neutral0,
    iconInteractiveInactive = Neutral1,
    error = FunctionalRed,
    gradient6_1 = listOf(Shadow4, Ocean3, Shadow2, Ocean3, Shadow4),
    gradient6_2 = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4),
    gradient3_1 = listOf(Shadow2, Ocean3, Shadow4),
    gradient3_2 = listOf(Rose2, Lavender3, Rose4),
    gradient2_1 = listOf(Yellow1, Yellow2),
    gradient2_2 = listOf(Ocean3, Shadow3),
    gradient_yellow= listOf(Yellow1,Yellow2),
    gradient_pink= listOf(Pink1,Pink2),
    gradient_orange= listOf(Orange1, Orange2),
    gradient_green= listOf(Green1,Green2),
    gradient_blue= listOf(Blue1, Blue2),
    gradient_brown= listOf(Brown1, Brown2),
    gradient_cream= listOf(Cream1,Cream2),
    gradient_bluegreen= listOf(BlueGreenGradient1, BlueGreenGradient2),
    gradient_grey= listOf(Grey1, Grey2),
    isDark = false
)

private val DarkColorPalette = TipcalcColorPalette(
    primary = violet500,
    primaryVariant = purple700,
    secondary = orange200,
    background = black,
    backgroundreverse = white,
    brand = Shadow1,
    bgcolor = bgblack,
    uiBackground = Neutral8,
    uiBorder = Neutral3,
    uiFloated = FunctionalDarkGrey,
    textPrimary = Shadow1,
    textSecondary = Neutral0,
    textHelp = Neutral1,
    textInteractive = Neutral7,
    textLink = Ocean2,
    iconPrimary = Shadow1,
    iconSecondary = Neutral0,
    iconInteractive = Neutral7,
    iconInteractiveInactive = Neutral6,
    error = FunctionalRedDark,
    gradient6_1 = listOf(Shadow5, Ocean7, Shadow9, Ocean7, Shadow5),
    gradient6_2 = listOf(Rose11, Lavender7, Rose8, Lavender7, Rose11),
    gradient3_1 = listOf(Shadow9, Ocean7, Shadow5),
    gradient3_2 = listOf(Rose8, Lavender7, Rose11),
    gradient2_1 = listOf(Shadow5, Rose8),
    gradient2_2 = listOf(Ocean7, Shadow7),
    gradient_yellow= listOf(darkYellow1,darkYellow2),
    gradient_pink= listOf(darkPink1,darkPink2),
    gradient_orange= listOf(darkOrange1, darkOrange2),
    gradient_green= listOf(darkGreen1,darkGreen2),
    gradient_blue= listOf(darkBlue1, darkBlue2),
    gradient_brown= listOf(darkBrown1, darkBrown2),
    gradient_cream= listOf(darkCream1,darkCream2),
    gradient_bluegreen= listOf(darkBlueGreenGradient1, darkBlueGreenGradient2),
    gradient_grey= listOf(darkGrey1, darkGrey2),
    isDark = true
)

@Composable
fun TipcalcTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    val sysUiController = SysUiController.current
    onCommit(sysUiController, colors.uiBackground) {
        sysUiController.setSystemBarsColor(
            color = colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    }

    ProvideTipcalcColors(colors) {
        MaterialTheme(
            colors = debugColors(darkTheme,colors,MaterialTheme.colors),
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

object TipcalcTheme {
    @Composable
    val colors: TipcalcColorPalette
        get() = TipcalcColorAmbient.current
}

/**
 * Tipcalc custom Color Palette
 */
@Stable
class TipcalcColorPalette(

    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    background: Color,
    backgroundreverse: Color,
    bgcolor: Color,


    gradient6_1: List<Color>,
    gradient6_2: List<Color>,
    gradient3_1: List<Color>,
    gradient3_2: List<Color>,
    gradient2_1: List<Color>,
    gradient2_2: List<Color>,
    gradient_yellow: List<Color>,
    gradient_pink: List<Color>,
    gradient_orange: List<Color>,
    gradient_green: List<Color>,
    gradient_blue: List<Color>,
    gradient_brown: List<Color>,
    gradient_cream: List<Color>,
    gradient_bluegreen: List<Color>,
    gradient_grey: List<Color>,
    brand: Color,
    uiBackground: Color,
    uiBorder: Color,
    uiFloated: Color,

    interactivePrimary: List<Color> = gradient2_1,
    interactiveSecondary: List<Color> = gradient2_2,
    interactiveMask: List<Color> = gradient6_1,
    textPrimary: Color = brand,
    textSecondary: Color,
    textHelp: Color,
    textInteractive: Color,
    textLink: Color,
    iconPrimary: Color = brand,
    iconSecondary: Color,
    iconInteractive: Color,
    iconInteractiveInactive: Color,
    error: Color,
    notificationBadge: Color = error,
    isDark: Boolean
) {
    var gradient6_1 by mutableStateOf(gradient6_1)
        private set
    var gradient6_2 by mutableStateOf(gradient6_2)
        private set
    var gradient3_1 by mutableStateOf(gradient3_1)
        private set
    var gradient3_2 by mutableStateOf(gradient3_2)
        private set
    var gradient2_1 by mutableStateOf(gradient2_1)
        private set
    var gradient2_2 by mutableStateOf(gradient2_2)
        private set


    var gradient_yellow by mutableStateOf(gradient_yellow)
        private set
    var gradient_pink by mutableStateOf(gradient_pink)
        private set
    var gradient_orange by mutableStateOf(gradient_orange)
        private set
    var gradient_green by mutableStateOf(gradient_green)
        private set
    var gradient_blue by mutableStateOf(gradient_blue)
        private set
    var gradient_brown by mutableStateOf(gradient_brown)
        private set
    var gradient_cream by mutableStateOf(gradient_cream)
        private set
    var gradient_bluegreen by mutableStateOf(gradient_bluegreen)
        private set
    var gradient_grey by mutableStateOf(gradient_grey)
        private set

    var primary by mutableStateOf(primary)
        private set
    var primaryVariant by mutableStateOf(primaryVariant)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var background by mutableStateOf(background)
        private set

    var backgroundreverse by mutableStateOf(backgroundreverse)
        private set

 var bgcolor by mutableStateOf(bgcolor)
        private set

    var brand by mutableStateOf(brand)
        private set
    var uiBackground by mutableStateOf(uiBackground)
        private set
    var uiBorder by mutableStateOf(uiBorder)
        private set
    var uiFloated by mutableStateOf(uiFloated)
        private set
    var interactivePrimary by mutableStateOf(interactivePrimary)
        private set
    var interactiveSecondary by mutableStateOf(interactiveSecondary)
        private set
    var interactiveMask by mutableStateOf(interactiveMask)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textHelp by mutableStateOf(textHelp)
        private set
    var textInteractive by mutableStateOf(textInteractive)
        private set
    var textLink by mutableStateOf(textLink)
        private set
    var iconPrimary by mutableStateOf(iconPrimary)
        private set
    var iconSecondary by mutableStateOf(iconSecondary)
        private set
    var iconInteractive by mutableStateOf(iconInteractive)
        private set
    var iconInteractiveInactive by mutableStateOf(iconInteractiveInactive)
        private set
    var error by mutableStateOf(error)
        private set
    var notificationBadge by mutableStateOf(notificationBadge)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: TipcalcColorPalette) {
        gradient6_1 = other.gradient6_1
        gradient6_2 = other.gradient6_2
        gradient3_1 = other.gradient3_1
        gradient3_2 = other.gradient3_2
        gradient2_1 = other.gradient2_1
        gradient2_2 = other.gradient2_2
        gradient_yellow= other.gradient_yellow
        gradient_pink= other.gradient_pink
        gradient_orange= other.gradient_orange
        gradient_green= other.gradient_green
        gradient_blue= other.gradient_blue
        gradient_brown= other.gradient_brown
        gradient_cream= other.gradient_cream
        gradient_bluegreen= other.gradient_bluegreen
        gradient_grey= other.gradient_grey
        brand = other.brand
        uiBackground = other.uiBackground
        bgcolor = other.bgcolor
        uiBorder = other.uiBorder
        uiFloated = other.uiFloated
        primary = other.primary
        primaryVariant = other.primaryVariant
        background = other.background
        secondary = other.secondary
        backgroundreverse = other.backgroundreverse
        interactivePrimary = other.interactivePrimary
        interactiveSecondary = other.interactiveSecondary
        interactiveMask = other.interactiveMask
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textHelp = other.textHelp
        textInteractive = other.textInteractive
        textLink = other.textLink
        iconPrimary = other.iconPrimary
        iconSecondary = other.iconSecondary
        iconInteractive = other.iconInteractive
        iconInteractiveInactive = other.iconInteractiveInactive
        error = other.error
        notificationBadge = other.notificationBadge
        isDark = other.isDark
    }
}

@Composable
fun ProvideTipcalcColors(
    colors: TipcalcColorPalette,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    Providers(TipcalcColorAmbient provides colorPalette, children = content)
}

private val TipcalcColorAmbient = staticAmbientOf<TipcalcColorPalette> {
    error("No TipcalcColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [MaterialTheme.colors] in preference to [TipcalcTheme.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: TipcalcColorPalette,
    colors: Colors
) = Colors(
    primary = debugColor.primary,
    primaryVariant = debugColor.primaryVariant,
    secondary = debugColor.secondary,
    secondaryVariant = colors.secondaryVariant,
    background = debugColor.background,
    surface = colors.surface,
    error = colors.error,
    onPrimary = colors.onPrimary,
    onSecondary = colors.onSecondary,
    onBackground = colors.onBackground,
    onSurface = colors.onSurface,
    onError = colors.onError,
    isLight = !darkTheme
)


