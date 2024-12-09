package com.dsyang92.raytracerchallenge

import imgui.ImGui
import imgui.app.Application
import imgui.app.Configuration
import imgui.flag.ImGuiConfigFlags

class ImGuiMain : Application() {

    override fun configure(config: Configuration) {
        config.title = "Ray Tracer Challenge"
    }

    override fun initImGui(config: Configuration) {
        super.initImGui(config)

        val io = ImGui.getIO()
        io.iniFilename = null;                                // We don't want to save .ini file
        io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);  // Enable Keyboard Controls
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);      // Enable Docking
        io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);    // Enable Multi-Viewport / Platform Windows
        io.configViewportsNoTaskBarIcon = true;
    }


    override fun process() {
        ImGui.showDemoWindow()
        ImGui.begin("Settings")
        if(ImGui.button("Render")) {
            renderImage()
        }
        ImGui.end()
    }

    private fun renderImage() {
        ImGui.text("render complete")
    }
}

fun main() = Application.launch(ImGuiMain())